<template>
    <div id="board-list">
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in boardList" v-bind:key="item">
                    <td>{{ item.bno }}</td>
                    <td><router-link :to="{ name: 'detail', params: {bno: item.bno} }">{{ item.title }}</router-link></td>
                    <td>{{ item.contents }}</td>
                    <td>{{ item.writer }}</td>
                    <td>{{ item.viewcnt }}</td>
                    <td>{{ item.regdate }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import Axios from "axios";

    export default {
       data: function() {
           return {
                boardList : []
           }
       }
       , created: function() {
        //    console.log("created");
       }
       , mounted: function() {
        //    console.log("mounted");
           try {
               this.fnList();
           } catch(e) {
               console.log(e);
           }
       }
       , methods: {
           fnList: function() {
               let vm = this;

               let sendData = {};
               
               Axios.post("/vue_home", sendData)
               .then(function(res) {
                   vm.boardList = res.data.fileDtoList;
               })
               .catch(function(ex) {
                   console.log(ex);
               })
           }
       }
    };
</script>