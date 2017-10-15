<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>$Title$</title>
    <script src="https://unpkg.com/vue"></script>
</head>
<body>

<div id="app-5">
    <p>{{ message }}</p>
    <button onclick="bb()" v-on:click="reverseMessage">逆转消息</button>
</div>
<script>
    var app5 = new Vue({
        el: '#app-5',
        data: {
            message: 'Hello Vue.js!'
        },
        methods: {
            reverseMessage: function () {
                this.message = this.message.split('').reverse().join('')
            }
        }
    })

    function bb() {
        alert("bb");
    }
</script>
</body>
</html>