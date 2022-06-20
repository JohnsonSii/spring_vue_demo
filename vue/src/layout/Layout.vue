<template>
  <div style="background-color: #fff">
    <HeaderMenu />

    <div style="display: flex">
      <div>
        <Aside />
      </div>
      <div style="flex: 1">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderMenu from "@/components/HeaderMenu.vue";
import Aside from "@/components/Aside.vue";

export default {
  name: "Layout",
  components: {
    HeaderMenu,
    Aside,
  },
}
</script>

<style scoped>

</style>