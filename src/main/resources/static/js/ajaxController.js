/**
 * Created by andyx on 14/5/18.
 */
$(function() {
    /*  Submit form using Ajax */
    $('button[type=submit]').click(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        //Remove all errors
        $('input').next().remove();

        $.post({
            url : 'newUser',
            data : $('form[name=employeeForm]').serialize(),
            success : function(res) {
                if(res.codeStatus == "1") {
                    $('#myTable tbody').empty();
                    $.each(res.users, function(i, user){

                        var customerRow = '<tr>' +
                            '<td>' + user.id + '</td>' +
                            '<td>' + user.name + '</td>' +
                            '<td>' + user.email + '</td>' +
                            '</tr>';
                        $('#myTable tbody').append(customerRow);
                    });
                }
                alert(res.message);
            }
        })
    });
});
