var vue = new Vue({
    el:"#app",
    data:{
        userList:[],
        user:{}
    },
    methods:{
        // 查询所有
        findAll:function () {
            var _this = this;
            axios.get("./user/findAll").then(function (response) {
                // this在axios中不再是一个vue对象了，this表示window对象 //ES5的版本，vue和axios整合不够全面
                // alert("ok");
                // window.alert("ok1");
                // this.alert("ok2");
                _this.userList = response.data
            }).catch(function (error) {

            })
        },
        // // ID查询（RestFul风格）
        // findById:function (id) {
        //     var _this = this;
        //     axios.get("./user/findById/"+id).then(function (response) {
        //         _this.user = response.data
        //     }).catch(function (error) {
        //
        //     })
        // }
        // ID查询（传统风格）
        findById:function (id) {
            var _this = this;
            axios.get("./user/findById",{params:{id:id}}).then(function (response) {
                _this.user = response.data
            }).catch(function (error) {

            })
        },
        // 更新
        update:function () {
            var _this = this;
            axios.post("./user/update",this.user).then(function (response) {
                // 刷新页面
                _this.findAll();
            }).catch(function (error) {
                alert(error);
            })
        }
    },
    created:function () {
        //初始化页面
        this.findAll();
    }
})