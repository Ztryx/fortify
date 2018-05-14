/**
 * Created by andyx on 14/5/18.
 */
$(function() {
    /*  Submit form using Ajax */
    $('button[type=submit]').click(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        switch (checkGeneral()) {
            case 0:
                alert("The field name cannot be empty")
                break;
            case 1:
                alert("The field email cannot be empty")
                break;
            case 2:
                alert("The field email cannot have this format")
                break;
            case 3:
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
                    },
                    error: function(res) {
                        alert(res);
                    }
                });
                break;
            default:
                break;
        }
    });

    function checkGeneral() {
        if($("input[name=name]").val() === "")
            return 0;
        else if($("input[name=email]").val()  === "")
            return 1;
        else if (!checkEmail($("input[name=email]").val()))
            return 2;
        else
            return 3;
    };

    function checkEmail(email) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    };
});
