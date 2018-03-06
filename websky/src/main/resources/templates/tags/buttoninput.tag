<div class="form-group col-md-6 col-sm-6 ">
    <label class="control-label col-md-4 col-sm-4 " for="${id}">${label}</label>
    <div class=" col-sm-6 " style="margin-right: 0px;padding-right: 0px">
        <input type="text" id="${id}" name="${id}" value="${value!}" class="form-control" readonly />
    </div>
    <div class="col-md-2 col-sm-2" style="margin: 0px;padding: 0px" onclick="${clickFun}">
        <button type="button" class="btn btn-primary glyphicon glyphicon-search" style="margin-top: -1px;">
        </button>
    </div>
</div>