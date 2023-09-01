<script lang="ts" setup>
import { BmMark } from "@/types/utils";
import { getBuildLocaltion } from "@/api/build";

const buildMarker = ref<BmMark[]>([]);

const mapConfig = ref({
  center: { lat: 28.225525204117517, lng: 112.92573134402001 },
  zoom: 18,
  scrollWheelZoom: true,
  click: (e: any) => {
    console.log(e.point);
  },
});
onMounted(() => {
  getBuildLocaltion().then(({ data }) => {
    buildMarker.value = data;
  });
});
</script>

<template>
  <baidu-map
    :center="mapConfig.center"
    :scroll-wheel-zoom="mapConfig.scrollWheelZoom"
    :zoom="mapConfig.zoom"
    class="map"
    @click="mapConfig.click"
  >
    <bm-marker
      v-for="marker in buildMarker"
      :dragging="false"
      :position="marker.position"
      :title="marker.title"
    />
  </baidu-map>
</template>

<style scoped>
.map {
  width: 100%;
  height: 100ch;
}
</style>
