<template>
  <el-carousel :autoplay="true" :interval="5000" arrow="always" indicator-position="outside">
    <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
      <!-- 轮播项的内容 -->
      <div class="carousel-item-content">
        <img :src="item.image" alt="Slide Image">
        <div class="carousel-item-caption">
          <h3>{{ item.title }}</h3>
          <p>{{ item.description }}</p>
        </div>
      </div>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  data() {
    return {
      currentIndex: 0,
      carouselItems: [
        {
          image: '../assets/images/image2.jpg',
          title: '标题1',
          description: '描述1'
        },
        {
          image: '@/assets/images/123.jpg',
          title: '标题2',
          description: '描述2'
        },
        {
          image: '../assets/images/image3.jpg',
          title: '标题3',
          description: '描述3'
        }
      ]
    };
  },
  mounted(){
    this.startCarousel();
  },
  methods:{
    startCarousel() {
    setInterval(() => {
      this.currentIndex = (this.currentIndex + 1) % this.carouselItems.length;
    }, 5000); // 切换间隔时间，单位为毫秒
  }

  }
};
</script>

<style scoped>
.carousel-item-content {
  position: relative;
  height: 600px; /* 调整轮播图的高度 */
  background-color: #000000; /* 自定义背景颜色 */
}

.carousel-item-caption {
  position: absolute;
  bottom: 20px;
  left: 20px;
  color: #ffffff; /* 自定义字体颜色 */
}

.carousel-item-caption h3 {
  font-size: 24px; /* 自定义标题字体大小 */
}

.carousel-item-caption p {
  font-size: 16px; /* 自定义描述字体大小 */
}
</style>
