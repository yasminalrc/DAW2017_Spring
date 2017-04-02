var paginaActual = 0;

$(document).ready(function () {
    cargarPagina(paginaActual);
})

function cargarPagina(num) {
    $.ajax({
        type: "GET"
        , beforeSend: function() {
            $('#loading').html("<img src='/ellipsis.gif'/>").show();
            $('#loading').html("<img src='/ellipsis.gif'/>");
        }
        , contentType: "application/json"
        , url: "/admin/?page="+num
        , success: function (data) {
            //console.log(data);
            var toAppend = '';
            for (var i = 0; i < data.length; i++) {
            	/*
                if (data[i] == null){
                    var prod = "";
                }else{
                    var prod = data[i].name
                }
                */
                
                 toAppend += "<tr><td><input type='checkbox'>"+"<a href='#'>"+"<i class='icon-star-empty'>"+"</i></a>"+"</td><td><img class ='img-responsive' style='border:1px solid #ddd; height: 70px; width: 70px;' src='/files/"+data[i].image+"'/></td><td><p>"+data[i].id+"</p></td><td><p>"+data[i].name+"</p></td><td><p>"+data[i].brand+"</p></td><td><p>"+data[i].type+"</p></td><td><p>"+data[i].published+"</p></td><td><a href='/admin/"+data[i].id+"' type='button' class='btn btn-primary'> <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a><a href='/admin/product/delete/"+data[i].id+".html' type='button' class='btn btn-primary' > <span class='glyphicon glyphicon-trash' aria-hidden='true'></span></a></td></tr>";
            }
            paginaActual = paginaActual+1;
            toAppend += "<div id='pagina" + paginaActual + "'></div>"
            $("#pagina" + (paginaActual-1)).append(toAppend);
            $('#loading').html("").hide();
        }
    });
}