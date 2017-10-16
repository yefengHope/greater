<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>复杂对象ajax提交</title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
</head>
<body>

<form id="form2"
<#--action="/ajax/upload" -->
<#--action="/ajax/uploadUserAndFile"-->
<#--action="/ajax/uploadUserFileVo"-->
<#--action="/ajax/uploadUserAndFileRequest"-->
>
    <input type="text" name="name">

    <select multiple name="phone">
        <option value="1311">1311</option>
        <option value="1312">1312</option>
        <option value="1313">1313</option>
        <option value="1314">1314</option>
    </select>

    <button type="button" onclick="uploadJqueryAjax()">JqueryAjax Submit</button>
    <button type="button" onclick="uploadJqueryForm()">JqueryForm Submit</button>
</form>

<script>

    function uploadJqueryAjax() {
        $.ajax({
            url: "/complexObject/save",
            data: $("#form2").serialize(),
        })
    }

    function uploadJqueryForm() {

        $("#form2").ajaxForm({
            type: 'post',
            url: "/complexObject/save",
            success: function (data) {
                console.log(data);
            },
            dataType: "json"
        }).submit();
    }

</script>
</body>
</html>