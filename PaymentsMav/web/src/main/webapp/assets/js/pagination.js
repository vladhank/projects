$(document).ready(function () {
    $(document).ajaxComplete(function () {
        paginate('#myTableId',10);
        function paginate(tableName,RecordsPerPage) {
            $('#nav').remove();
            $(tableName).after('<div id="nav"></div>');
            var rowsShown = RecordsPerPage;
            var rowsTotal = $(tableName + ' tbody tr').length;
            var numPages = rowsTotal / rowsShown;
            for (i = 0; i < numPages; i++) {
                var pageNum = i + 1;
                $('#nav').append('<a href="#" rel="' + i + '">' + pageNum + '</a> ');
            }
            $(tableName + ' tbody tr').hide();
            $(tableName + ' tbody tr').slice(0, rowsShown).show();
            $('#nav a:first').addClass('active');
            $('#nav a').bind('click', function () {

                $('#nav a').removeClass('active');
                $(this).addClass('active');
                var currPage = $(this).attr('rel');
                var startItem = currPage * rowsShown;
                var endItem = startItem + rowsShown;
                $(tableName + ' tbody tr').css('opacity', '0.0').hide().slice(startItem, endItem).
                    css('display', 'table-row').animate({ opacity: 1 }, 300);
            });
        }
         });
});