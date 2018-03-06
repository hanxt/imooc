<%
    if(isEmpty(target)){
        target = -1;
    }

    if(isEmpty(disabled)){
        disabled = "";
    }else {
        disabled='disabled="disabled"';
    }
%>
<div class="form-group col-md-6 col-sm-6 ">
    <label class="control-label col-md-4 col-sm-4 " for="${id}">${label}</label>
    <div class="col-md-8 col-sm-8 ">
        <select id="${id}" name="${name!}" class="form-control" ${disabled}>
            <option></option>
            <%for(dict in session.dicts){
                if(diccode == dict.dicCode){
                    println(type.name(target));
                    if(type.name(target) == 'Integer' && target == parseInt(dict.dicValue)){
                        println( '<option value="' + dict.dicValue + '" selected="selected" >' + dict.dicName + '</option>');
                    }if(type.name(target) == 'Byte' && target == parseInt(dict.dicValue)){
                        println( '<option value="' + dict.dicValue + '" selected="selected" >' + dict.dicName + '</option>');
                    }else if(type.name(target) == 'String' && target == dict.dicValue){
                        println( '<option value="' + dict.dicValue + '" selected="selected" >' + dict.dicName + '</option>');
                    }else{
                        println( '<option value="' + dict.dicValue + '" >' + dict.dicName + '</option>');
                    }
                }
            }%>
        </select>
    </div>
</div>