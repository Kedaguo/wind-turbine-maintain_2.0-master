<template>
    <el-container class="container">
        <el-header>
            <el-menu default-active="echarts" router="true" class="el-menu-demo" mode="horizontal"
                background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" @select="handleSelect">
                <el-menu-item index="echarts" @click="gotoEcharts">数据中心</el-menu-item>
                <el-submenu index="2">
                    <template slot="title">我的工作台</template>
                    <el-menu-item index="2-1">选项1</el-menu-item>
                    <el-menu-item index="2-2">选项2</el-menu-item>
                    <el-menu-item index="2-3">选项3</el-menu-item>
                    <el-submenu index="2-4">
                        <template slot="title">选项4</template>
                        <el-menu-item index="2-4-1">选项1</el-menu-item>
                        <el-menu-item index="2-4-2">选项2</el-menu-item>
                        <el-menu-item index="2-4-3">选项3</el-menu-item>
                    </el-submenu>
                </el-submenu>
                <el-menu-item index="operate">消息中心</el-menu-item>
                <el-menu-item index="windturbine">风电监控</el-menu-item>
                <el-menu-item disabled>
                    <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
                        <div class="avatar-wrapper">
                            <img :src="avatar" class="user-avatar">
                            <i class="el-icon-caret-bottom" />
                        </div>
                        <el-dropdown-menu slot="dropdown">
                            <router-link to="/user/profile">
                                <el-dropdown-item>个人中心</el-dropdown-item>
                            </router-link>
                            <el-dropdown-item @click.native="setting = true">
                                <span>布局设置</span>
                            </el-dropdown-item>
                            <el-dropdown-item divided @click.native="logout">
                                <span>退出登录</span>
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-menu-item>
            </el-menu>

            <div class="line"></div>
        </el-header>
        <el-main>
            <router-view />
        </el-main>
    </el-container>
</template>
  
<script>
export default {
    name: "Index",
    data() {
        return {

        };
    },
    methods: {
        gotoEcharts() {
            this.$router.push('/student/echarts')
        },
        gotoWindturbine() {
            // this.activeIndex = 
        },
        // 顶部导航栏选择下拉菜单
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        async logout() {
            this.$confirm('确定注销并退出系统吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('LogOut').then(() => {
                    location.href = '/index';
                })
            }).catch(() => { });
        }

    }
};
</script>
 
<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.el-container {
    height: 100%;
    width: 100%;
}

.el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    padding: 0;
}


.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    padding: 0;
}

body>.el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}
</style>  
<style lang="scss" scoped>
.avatar-container {
    margin-right: 30px;

    .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
            cursor: pointer;
            width: 40px;
            height: 40px;
            border-radius: 10px;
        }

        .el-icon-caret-bottom {
            cursor: pointer;
            position: absolute;
            right: -20px;
            top: 25px;
            font-size: 12px;
        }
    }
}
</style>
  