<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ajax包含file提交</title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
</head>
<body>

<form id="form2" enctype="multipart/form-data" method="post"
      <#--action="/ajax/upload" -->
      <#--action="/ajax/uploadUserAndFile"-->
      <#--action="/ajax/uploadUserFileVo"-->
      action="/ajax/uploadUserAndFileRequest"
>
    <input type="text" name="name">
    <input type="text" name="phone">

    <input type="file" name="image" multiple>

    <button type="button" onclick="uploadFormData()">FormData Submit</button>
    <button type="button" onclick="uploadJqueryForm()">JqueryForm Submit</button>
</form>


<script>
    //using jquery.form.js
    function uploadJqueryForm(){
        $('#result').html('');

        $("#form2").ajaxForm({
            success:function(data) {
                $('#result').html(data);
            },
            dataType:"text"
        }).submit();
    }

    //using FormData() object
    function uploadFormData(){
        $('#result').html('');

        var oMyForm = new FormData();
        oMyForm.append("file", file2.files[0]);

        $.ajax({
            url: 'http://localhost:8080/spring-mvc-file-upload/rest/cont/upload',
            data: oMyForm,
            dataType: 'text',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function(data){
                $('#result').html(data);
            }
        });
    }
</script>
</body>
</html>