<template>
    <el-container>
        <el-aside width="200px">
            <el-menu default-active="windturbine/online" router="true" class="el-menu-vertical-demo" @open="handleOpen"
                @close="handleClose" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
                <el-menu-item index="windturbine/online">
                    <i class="el-icon-menu"></i>
                    <span slot="title">在线风机</span>
                </el-menu-item>
                <el-menu-item index="2">
                    <i class="el-icon-document"></i>
                    <span slot="title">维修风机</span>
                </el-menu-item>
                <el-menu-item index="3">
                    <i class="el-icon-setting"></i>
                    <span slot="title">保养风机</span>
                </el-menu-item>
                <el-menu-item index="4">
                    <i class="el-icon-setting"></i>
                    <span slot="title">维修单</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <el-main>
            <router-view></router-view>
        </el-main>

    </el-container>
</template>

<script>
export default {
    data() {
        return {

        }
    },
    computed: {},
    watch: {},
    created() { },
    mounted() { },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }
    }
}

</script>
<style>
.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
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