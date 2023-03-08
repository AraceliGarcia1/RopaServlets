// Función para llenar la tabla de prendas
const fill = (listPrendas) => {
    let table = "";
    if (listPrendas.length > 0) {
        for (let i = 0; i < listPrendas.length; i++) {
            table += `
        <tr>
         <td>${listPrendas[i].id}</td>
          <td>${listPrendas[i].nombre}</td>
          <td>${listPrendas[i].marca}</td>
          <td>${listPrendas[i].talla}</td>
          <td>${listPrendas[i].color}</td>
          <td>${listPrendas[i].descuento}</td>
          <td>$${listPrendas[i].costo.toFixed(2)}</td>
          <td>${listPrendas[i].stock}</td>
          <td>${listPrendas[i].status ? "Activo" : "Inactivo"}</td>
          <td>
              <button class="btn btn-warning delete-button" style="margin-right:10px ;" data-bs-toggle="modal" data-bs-target="#formModal" id="modificar"  onclick="updateP(${listPrendas[i].id})" >Modificar</button>
           <button class="btn btn-danger edit-button"  style="margin-left: 10px;"  onclick="deleteP(${listPrendas[i].id})" id="eliminar">Eliminar</button>
          </td>
        </tr>
      `;
        }
    } else {
        table = `
      <tr class="text-center">
        <td colspan="10">No hay registros para mostrar</td>
      </tr>
    `;
    }
    $('#prenda-table > tbody').html(table);

};

const findAll = () => {
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'GET',
        url: contextPath + '/readPrendas',
        data: { }
    }).done(function(res){
        console.log(res);
        fill(res.listPrendas);
    });
};

findAll();

const deleteP = (id) => {

    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
        console.log(id);
    $.ajax({
            type: 'POST',
            url: contextPath + '/deletePrendas',
            data: {
                id: id,
                'action':'delete',
                status: false
            }
        }).done(function(res){
            console.log('cambia el estado')
            const index = res.listPrendas.findIndex(prenda => prenda.id == id);
            if (index !=-1) {
            res.listPrendas[index].status = false; // actualizar el estado a false
                fill(res.listPrendas);
                $(`#prenda-table > tbody > tr:eq(${index}) .delete-button`).hide();
                $(`#prenda-table > tbody > tr:eq(${index}) .edit-button`).hide();


        }
        });


};

const registrar=()=>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    var data=new FormData()
    data.append("nombre",document.getElementById("nombre").value);
    data.append("marca",document.getElementById("marca").value);
    data.append("talla",document.getElementById("talla").value);
    data.append("color",document.getElementById("color").value);
    data.append("descuento",document.getElementById("descuento").value);
    data.append("costo",document.getElementById("costo").value);
    data.append("stock",document.getElementById("stock").value);
    data.append("action","create");
    $.ajax({
        type:"POST",
        url: contextPath + "/createPrendas",
        data:data,
        processData:false,
        contentType:false
    }).done(function (res){
        console.log(res);
        $(`#formModalR`).hide();
        fill(res.listPrendas);
        location.reload();
    });

};

const updateP=(id)=>{
    console.log("idPrenda")
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    console.log(id)
    $.ajax({
        type:'GET',
        url: contextPath + "/findByIdP",
        data:{id:id},
        'action':'findById'
    }).done(function (response){
        let prenda = response.listPrendas;
        prenda = prenda.filter(p => {
            return p.id == id ? true: false;
        })[0];
        console.log('esta es la prenda',prenda)
        if (prenda){
        $('#id1').val(prenda.id);
        $('#nombre1').val(prenda.nombre);
        $('#marca1').val(prenda.marca);
        $('#talla1').val(prenda.talla);
        $('#color1').val(prenda.color);
        $('#desc1').val(prenda.descuento);
        $('#costo1').val(prenda.costo);
        $('#stock1').val(prenda.stock);
        $('#status').val(prenda.status);
        }else{
            console.log(`No se encontró ninguna prenda con el ID ${id}.`)
        }
    });

};

const updateS=()=>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    console.log("me has presionado")
    var data=new FormData()
    data.append("id",document.getElementById("id1").value);
    data.append("nombre",document.getElementById("nombre1").value);
    data.append("marca",document.getElementById("marca1").value);
    data.append("talla",document.getElementById("talla1").value);
    data.append("color",document.getElementById("color1").value);
    data.append("descuento",document.getElementById("desc1").value);
    data.append("costo",document.getElementById("costo1").value);
    data.append("stock",document.getElementById("stock1").value);
    data.append("action","update");

    $.ajax({
        type:"POST",
        url: contextPath + "/updatePrendas",
        data:data,
        'action':'update',
        processData:false,
        contentType:false
    }).done(function (res){

        $(`#formModal`).hide();
        fill(res.listPrendas);
        location.reload();
    });


};









