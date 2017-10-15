<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Freemarker</title>
    <script src="https://unpkg.com/vue"></script>
</head>
<body>
<div id="app-7">
    <ol>
        <!--
          现在我们为每个 todo-item 提供 todo 对象
          todo 对象是变量，即其内容可以是动态的。
          我们也需要为每个组件提供一个“key”，晚些时候我们会做个解释。
        -->
        <todo-item
                v-for="item in groceryList"
                v-bind:todo="item"
                v-bind:key="item.id"
        >
        </todo-item>

        <todo-item
                v-for="item in groceryList"
                v-bind:todo="item"
        >
        </todo-item>
        <todo-item
                v-for="item in groceryList"
        >
        </todo-item>
    </ol>
</div>
<script>
    Vue.component('todo-item', {
        props: ['todo'],
        template: '<li>{{ todo.text }}</li>'
    });
    var app7 = new Vue({
        el: '#app-7',
        data: {
            groceryList: [
                { id: 0, text: '蔬菜' },
                { id: 1, text: '奶酪' },
                { id: 2, text: '随便其他什么人吃的东西' }
            ]
        }
    })
</script>
</body>
</html>