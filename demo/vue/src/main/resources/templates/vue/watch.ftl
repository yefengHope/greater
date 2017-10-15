<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Freemarker</title>
    <script src="https://unpkg.com/vue"></script>
</head>
<body>

<div id="example">
    <input type="text" v-model:value="a">
</div>

<script>
    var data = { a: 1 }
    var vm = new Vue({
        el: '#example',
        data: data
    });

    vm.$data === data // => true
    vm.$el === document.getElementById('example') // => true

    // $watch 是一个实例方法
    vm.$watch('a', function (newValue, oldValue) {
        // 这个回调将在 `vm.a` 改变后调用
        console.log(oldValue + "->" + newValue);
    })
</script>
</body>
</html>