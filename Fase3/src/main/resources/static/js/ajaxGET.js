var paginaActual = 0;

$(document).ready(function () {
	console.log("Pagina " + paginaActual);
    cargarPagina(paginaActual);
})

function cargarPagina(num) {
    $.ajax({
    	datatype:"json",
        type: "GET"
        , beforeSend: function() {
            $('#loading').html("<img src='/images/spinner.gif'/>").show();
            $('#loading').html("<img src='/images/spinner.gif'/>");
        }
        , contentType: "application/json"
        , url: "/api/products/?page="+num+"&size=10"
        , success: function(data) {
            console.log(data);
            console.log("Pagina " + paginaActual);
            var toAppend = '';
            
            for (var i = 0; i < data.length; i++) {
                // console.log(data[i]);                
                toAppend += '<tr>' + 
                    			'<td>' + 
                    				'<input type="checkbox"> <a href="#"><i class="icon-star-empty"></i></a>' + 
                    		    '</td>' + 
                    			'<td>' + 
                    				'<img style="border:1px solid #ddd; height: 70px; width: 70px;" src="/files/' + data[i].image + '.jpg" />' + 
                    			'</td>' + 
                    			'<td>' + 
                    				'<p>' + data[i].id + '</p>' + 
                    			'</td>' +
                    			'<td>' +
                    				'<p>' + data[i].name + '</p>' + 
                    			'</td>' + 
                    			'<td>' + 
                    				'<p>' + data[i].brand + '</p>' + 
                    			'</td>' + 
                    			'<td>' + 
                    				'<p>' + data[i].type + '</p>' + 
                    			'</td>' +
                    			'<td>' + 
                    				'<p>' + data[i].published + '</p>' +
                    			'</td>' + 
                    			'<td>' + 
                    				'<a href="/admin/' + data[i].id + '" type="button" class="btn btn-primary" > <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>' + 
                    				'<a href="/admin/product/delete/' + data[i].id + '" type="button" class="btn btn-primary" > <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>' + 
                    			'</td>' + 
                    		'</tr>';
            }
            
            paginaActual = paginaActual+1;
            console.log("Pagina " + paginaActual);;
            console.log(toAppend);
            /*
            $("#cargarmas").click(cargarPagina(paginaActual)){
                $("page" + paginaActual).append(" <b>Appended text</b>.");
            }    
            $("#page" + (paginaActual-1)).append(toAppend);
            $('#loading').html("").hide();
            */
            $("#product-table").append(toAppend);
            $('#loading').html("").hide();
        }
    });
}