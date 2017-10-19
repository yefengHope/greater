<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vue 替换数组</title>
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
    <ul id="array">
        <li v-for="item in array ">{{item.name}}-{{item.sex}}
            <span v-if="item.other">
            <span v-for=" sub in item.other">{{sub.phone}}</span>

            </span>
        </li>
    </ul>
</div>


<script>
    var vm = new Vue({
        el: "#array",
        data: {
            array: [
                {name: "张一", sex: "男",other : [{phone:13438336891},{phone:134383} ]},
                {name: "萌萌", sex: "女"},
                {name: "张二", sex: "男"},
                {name: "小明", sex: "女"},
                {name: "张三", sex: "男"},
                {name: "小芳", sex: "女"},
                {name: "张四", sex: "男"},
                {name: "小清", sex: "女"},
                {name: "张五", sex: "男"},
            ]
        }
    });

    // ---  非变化数组 ---
    // filter(), concat() 和 slice()
    // 调用方式
    // 追加多个数组内容
    // vm.array = vm.array.concat([{name: "张一", sex: "男",other : [{phone:13438336891},{phone:134383} ] }, {name: "韩峰2", sex: "男2"}, {name: "张丽萍12", sex: "女12"}]);

    // 删除内容
    // vm.array = vm.array.slice(0,0);

    // 替换就先删除再追加



    //---  变化数组方法 ---
    // push()
    // pop()
    // shift()
    // unshift()
    // splice()
    // sort()
    // reverse()

    // vm.array.splice(0,0,{name: "韩峰1", sex: "男1"}, {name: "韩峰2", sex: "男2"}, {name: "张丽萍12", sex: "女12"});
</script>
</body>
</html>