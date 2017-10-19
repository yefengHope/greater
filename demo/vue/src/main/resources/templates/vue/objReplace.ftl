<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>对象的替换</title>
    <script src="https://unpkg.com/vue"></script>

    <script src="https://cdn.bootcss.com/jquery/3.2.1/core.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <form>
        <div class="form-group">
            <label for="name">name</label>
            <input name="name" v-model:value="user.name" type="text" class="form-control" id="name" placeholder="name">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input name="email" v-model:value="user.email" type="email" class="form-control" id="exampleInputEmail1"
                   placeholder="Email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input name="password" v-model:value="user.password" type="text" class="form-control" id="exampleInputPassword1"
                   placeholder="Password">
        </div>
        <div v-for="item in user.other">{{item}}</div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
<script>
    var vm = new Vue({
        el: 'form',
        data: {
            user: {
                name: "",
                email: "",
                password: "",
                other : [1,2,3,4],
            }
        }
    });

    /*
        // Object.assign() 方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象。它将返回目标对象。
        vm.user = Object.assign({}, vm.user, {
          password: 27,
          name: 'Vue Green',
          email :'182544@qq.com'
        })

        vm.user = Object.assign({}, vm.user, {})
     */
</script>
</body>
</html>