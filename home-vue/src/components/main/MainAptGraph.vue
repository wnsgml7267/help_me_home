<template>
    <b-container>
        <b-card 
            title="연도별 매매 거래량">
            <!-- <b-tabs v-for="(sido, index) in sidos" :key="index">
                <b-tab :title="sido">
                </b-tab>
            </b-tabs> -->
            <!-- <b-button-group v-model="selected">
                <b-button  v-for="(sido, index) in sidos" :key="index"
                @click="changeGraph(sido)">{{ sido }}</b-button>
            </b-button-group> -->
            <b-overlay :show="!loaded" style="padding:10px">
                <div>
                    <Bar
                        v-if="loaded"
                        id="my-chart-id"
                        :options="chartOptions"
                        :data="chartData"
                    />
                </div>
            </b-overlay>
        </b-card>
        <!-- <div>
            <img :src="imageSrc">
        </div> -->
    </b-container>
</template>

<script>
import http from "@/api/http";
import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, Colors } from 'chart.js'
// import { mapGetters } from "vuex";

ChartJS.register(Colors, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
    name: 'MainAptGraph',
    components: { 
        Bar,
    },
    data() {
        return {
            loaded: false,
            selected: "",
            chartData: {
                labels: [ '1', '2', '3'], //연도
                datasets: [ { 
                    data: [40, 20, 12],
                    label: '연도별 매매 거래 수',
                    // backgroundColor: '#f87979',
                    borderColor: '#36A2EB',
                    backgroundColor: '#9BD0F5',
                } ], //dealcnt
            },
            
            chartOptions: {
                responsive: true,
                color: '#f87979',
            },
            sidoDealInfo: [],
        }
    },
    mounted() {
        if (this.sidoDealInfo.length == 0) {
            this.getAptDealData();
        }

    },
    methods: {
        getAptDealData(){
            http
                .get(`/apt/sidoDealInfo`)
                .then(({data}) => {
                    this.sidoDealInfo = data;
                    this.chartData.labels = [];
                    this.chartData.datasets[0].data = [];
                    data.forEach((info) => {
                        // console.log(info)
                        this.chartData.labels.push(info.dealYear);
                        this.chartData.datasets[0].data.push(info.cnt);
                    })

                    // console.log(this.chartData)
                    this.loaded = true
                })
        },
    },
    computed: {
        // ...mapGetters(["sidos"])
    }
};
</script>

<style scoped></style>