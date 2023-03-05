// Función para llenar la tabla de prendas
const fill = (listPrendas) => {
    console.log(listPrendas)
    let table = "";
    if (listPrendas.length > 0) {
        for (let i = 0; i < listPrendas.length; i++) {
            table += `
        <tr>
          <td>${listPrendas[i].nombre}</td>
          <td>${listPrendas[i].marca}</td>
          <td>${listPrendas[i].talla}</td>
          <td>${listPrendas[i].color}</td>
          <td>${listPrendas[i].descuento}</td>
          <td>$${listPrendas[i].costo.toFixed(2)}</td>
          <td>${listPrendas[i].stock}</td>
          <td>${listPrendas[i].status ? "Activo" : "Inactivo"}</td>
          <td>
           <button class="btn btn-warning" style="margin-right:10px ;" data-bs-toggle="modal" data-bs-target="#formModal">Modificar</button>
           <button class="btn btn-danger"  style="margin-left: 10px;" type="submit">Eliminar</button>
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




