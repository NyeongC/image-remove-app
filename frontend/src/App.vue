<script setup>
import LbTest from './components/LbTest.vue'
</script>

<template>
  <div class="container">
    <h1>이미지 배경 제거 서비스</h1>

    <p>
      이미지를 업로드하면 Python Worker가
      비동기로 배경을 제거하기!
    </p>

    <button>이미지 업로드하기</button>

    <LbTest />
  </div>
</template>

<style>
.container {
  margin-top: 100px;
  text-align: center;
}

h1 {
  font-size: 40px;
}

p {
  margin-top: 20px;
  font-size: 18px;
}

button {
  margin-top: 30px;
  padding: 12px 24px;
  font-size: 16px;
  cursor: pointer;
}
</style>
