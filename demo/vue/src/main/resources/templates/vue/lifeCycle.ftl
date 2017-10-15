<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Vue生命周期及钩子函数</title>
    <script src="https://unpkg.com/vue"></script>
</head>
<body>

<div id="example">
    <input type="text" v-model:value="a"/>
</div>

<script>
    new Vue({
        el: "#example",
        data: {
            a: 1
        },
        created: function () {
            // `this` 指向 vm 实例
            console.log('a is: ' + this.a)
        }
    })
</script>
</body>
</html>