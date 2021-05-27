<template>
    <div>
        [[ {{ $route.params.bno }} ]]
            <div>제목 : {{ fileDetail.title }}</div>
            <div>작성자 : {{ fileDetail.writer }}</div>
            <div>내용 : {{ fileDetail.contents }}</div>
            <div>파일 원본명 : {{ fileDetail.org_fname }}</div>
            <div>파일 저장명 : {{ fileDetail.save_fname }}</div>
            <div>파일 경로 : {{ fileDetail.fpath }}</div>
            <button>다운로드</button>
            <button><router-link :to="{ name: 'update', params: {bno: fileDetail.bno} }">수정</router-link></button>
            <button>삭제</button>
            <button>목록</button>
    </div>
</template>

<script>
    import Axios from "axios";

    export default {
       data: function() {
           return {
            bno : "",
            fileDetail : {}
           }
       }
       , created: function() {
        //    console.log("created");
       }
       , mounted: function() {
        //    console.log("mounted");
           try {
               this.fnDetail();
           } catch(e) {
               console.log(e);
           }
       }
       , methods: {
           fnDetail: function() {
                let vm = this;
                vm.bno = this.$route.params.bno;

                let sendData = {
                    bno : vm.bno
                };

                Axios.post("/vue_detail", sendData)
                .then(function(res) {
                    console.log(res);
                    vm.fileDetail = res.data;
                })
                .catch(function(ex) {
                    console.log(ex);
                })
           }
       }
    };
</script>