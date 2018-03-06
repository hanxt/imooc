(function ($) {

   $.fn.pickList = function (options) {

      var opts = $.extend({}, $.fn.pickList.defaults, options);

      // 为select加option，也就是左侧框中的数据
      this.fill = function () {
         var option = '';

         $.each(opts.data, function (key, val) {
            if(val.type=='1'){
                 option += '<option data-id=' + val.id + '>' + val.text + '</option>';                 
            }      
         });
         this.find('.pickData').append(option);        
      };

      // 为select加option，也就是右侧框中的数据
      this.fill1 = function () {
         var option = '';
         $.each(opts.data, function (key, val) {
            if(val.type=='2'){
                 option += '<option data-id=' + val.id + '>' + val.text + '</option>';                
            }      
         });
         this.find('.pickListResult').append(option);        
      };

      // 实现按钮功能
      this.controll = function () {
         var pickThis = this;

         pickThis.find(".pAdd").on('click', function () {
            var p = pickThis.find(".pickData option:selected");
            p.clone().appendTo(pickThis.find(".pickListResult"));
            p.remove();
         });

         pickThis.find(".pAddAll").on('click', function () {
            var p = pickThis.find(".pickData option");
            p.clone().appendTo(pickThis.find(".pickListResult"));
            p.remove();
         });

         pickThis.find(".pRemove").on('click', function () {
            var p = pickThis.find(".pickListResult option:selected");
            p.clone().appendTo(pickThis.find(".pickData"));
            p.remove();
         });

         pickThis.find(".pRemoveAll").on('click', function () {
            var p = pickThis.find(".pickListResult option");
            p.clone().appendTo(pickThis.find(".pickData"));
            p.remove();
         });
      };

      // 定义空的数组对象objResult，将右侧框中的数据添加到objResult中
      this.getValues = function () {
         var objResult = [];
         this.find(".pickListResult option").each(function () {
            objResult.push({
               id: $(this).data('id'),
               text: this.text
            });
         });
         return objResult;
      };

      // 初始化数据
      this.init = function () {
         var pickListHtml =
                 "<div class='row'>" +
                 "  <div class='col-sm-5'>" +
                 "	 <select class='form-control pickListSelect pickData' multiple></select>" +
                 " </div>" +
                 " <div class='col-sm-2 pickListButtons'>" +
                 "	<button  class='pAdd btn btn-primary btn-sm'>" + opts.add + "</button>" +
                 "  <button  class='pAddAll btn btn-primary btn-sm'>" + opts.addAll + "</button>" +
                 "	<button  class='pRemove btn btn-primary btn-sm'>" + opts.remove + "</button>" +
                 "	<button  class='pRemoveAll btn btn-primary btn-sm'>" + opts.removeAll + "</button>" +
                 " </div>" +
                 " <div class='col-sm-5'>" +
                 "    <select class='form-control pickListSelect pickListResult' multiple></select>" +
                 " </div>" +
                 "</div>";

         this.append(pickListHtml);

         this.fill();// 为select加option，也就是左侧框中的数据
         this.fill1();
         this.controll();// 实现按钮功能
      };

      this.init();// 初始化数据
      return this;
   };

   $.fn.pickList.defaults = {
      add: '添加',
      addAll: '添加所有',
      remove: '删除',
      removeAll: '删除所有'
   };


}(jQuery));