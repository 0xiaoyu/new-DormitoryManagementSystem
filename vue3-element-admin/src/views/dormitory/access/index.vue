<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :inline="true" :model="queryParams">
        <el-form-item label="查询时间" prop="data">
          <el-date-picker
              v-model="queryParams.data"
              :shortcuts="shortcuts"
              end-placeholder="End date"
              range-separator="To"
              start-placeholder="Start date"
              type="datetimerange"
          />
        </el-form-item>
        <el-form-item label="查询类型">
          <el-select v-model="queryParams.type" placeholder="请选择查询类型">
            <el-option :key="0" :value="'0,1,2,3'" label="全部"/>
            <el-option :key="1" :value="'0,1'" label="学生进出"/>
            <el-option :key="2" :value="'2,3'" label="其他人员"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery()">
            <i-ep-search/>
            搜索
          </el-button>
          <el-button @click="resetQuery()">
            <i-ep-refresh/>
            重置
          </el-button>
          <el-button @click="resetQuery()">
            <i-ep-refresh/>
            设置门禁时间
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
const queryParams = reactive({
  data: '',
  type: '0,1,2,3'
})
const shortcuts = [
  {
    text: 'Last week',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
      return [start, end]
    },
  },
  {
    text: 'Last month',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
      return [start, end]
    },
  },
  {
    text: 'Last 3 months',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
      return [start, end]
    },
  },
]
</script>
<style scoped>

</style>
