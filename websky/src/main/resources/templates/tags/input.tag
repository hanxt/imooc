<%
    var colNumIn = "";
    var colNumLabel = "";
    var colNumInput = "";
    if(isEmpty(colNum)||colNum=="6"){
        colNumIn = "6";
        colNumLabel = "4";
        colNumInput = "8";
    }else if(colNum=="12"){
        colNumIn = "12";
        colNumLabel = "2";
        colNumInput = "10";
    }
%>
<div class="form-group col-md-${colNumIn} col-sm-${colNumIn} ">
    <label class="control-label col-md-${colNumLabel} col-sm-${colNumLabel} " for="${id}">${label}</label>
    <div class="col-md-${colNumInput} col-sm-${colNumInput} ">
        <input <% if(isNotEmpty(type)){ %>
                type="${type}"
            <%}else{%>
                type="text"
            <%}%>

                id="${id}" name="${id}" value ="${value!}" placeholder="${placeholder!}" ${readonly!} class="form-control ">
    </div>
</div>