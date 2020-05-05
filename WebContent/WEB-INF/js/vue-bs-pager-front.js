"use strict";

/*!
 * @author: lihaitao
 * @webSite: http://blog.csdn.net/lhtzbj12/
 * @version: v0.5
 * @opensource:
 * @description: ä½¿ç”¨åŸºäºŽVueå’ŒBootstrapçš„åˆ†é¡µå¯¼èˆªç»„ä»¶
 * @è°ƒç”¨ç¤ºä¾‹
 <bs-pager v-bind:total-count="totalcount" v-bind:page-size="pageSize"
  v-bind:page-index="pageIndex" v-on:page-change="pageChange"></bs-pager>
 */
Vue.component('bs-pager', {
    props: {
        totalCount: {
            //æ•°æ®æ€»æ¡æ•°
            type: Number,
            default: 0
        },
        pageSize: {
            //æ¯é¡µæ˜¾ç¤ºæ•°
            type: Number,
            default: 10,
            validator: function validator(value) {
                return value > 0;
            }
        },
        pageIndex: {
            //å½“å‰é¡µæ•°
            type: Number,
            default: 0
        },
        extClass: {
            //æ‰©å±•çš„æ ·å¼ pagination-lg  pagination-sm
            type: String,
            default: ''
        },
        showNearby: {
            //å½“å‰æŒ‰é’®å‰åŽæŒ‰é’®æ•°
            type: Number,
            default: 3,
            validator: function validator(value) {
                return value > 0;
            }
        },
        infoTemplate: {
            //åˆ†é¡µä¿¡æ¯æ¨¡æ¿
            type: String,
            default: 'æ€»å…± {{totalCount}} é¡¹ æ¯é¡µæ˜¾ç¤º {{pageSize}} é¡¹ å½“å‰ {{pageIndex}}/{{totalPage}} é¡µ'
        }
    },
    data: function data() {
        return {
            inputPageIndex: ''
        };
    },
    //template: '<div class="bs-pager clearfix"> \
    //                <div class="bs-pager-info " v-bind:class="extClass" >{{infoContent}}</div> \
    //                <ul class="pagination pull-right" v-bind:class="extClass"> \
    //                     <template v-for="p in navPages" > \
    //                        <pager-btn v-if="p !== \'...\'" v-bind:num="p" v-on:page-change="pageChange" v-bind:page-index="pageIndex"></pager-btn> \
    //                        <li v-else><span >{{p}}</span></li> \
    //                     </template>     \
    //                     <li v-if="showInputPage"><input type="text" v-model="inputPageIndex" v-on:change="inputPageChange"></li> \
    //                </ul> \
    //            </div> ', //è¿™é‡Œ v-on:pageChangeæ˜¯å¯ä»¥çš„ï¼Œä½†æ˜¯åœ¨domé‡Œåªèƒ½ç”¨page-change
    template: '<nav class="page_tag">\
        <ul class= "pagination" >\
            <li :class= "[\'page-item\' ,hasPrePage]">\
                <a class="page-link" href="javascript:void(0)" aria-label="Previous" title="ä¸Šä¸€é " @click="PrevPageChange()">\
                    <span aria-hidden="true">&laquo;</span>\
                    <span class="sr-only">Previous</span>\
                </a>\
            </li>\
            <template v-for="p in navPages" > \
                <pager-btn v-if="p !== \'...\'" v-bind:num="p" v-on:page-change="pageChange" v-bind:page-index="pageIndex"></pager-btn> \
                <li v-else><span >{{p}}</span></li> \
            </template>\
            <li :class="[\'page-item\' ,hasNextPage]">\
                <a class="page-link" href="javascript:void(0)" aria-label="Next" title="ä¸‹ä¸€é " @click="NextPageChange()">\
                    <span aria-hidden="true">&raquo;</span>\
                    <span class="sr-only">Next</span>\
                </a>\
            </li>\
        </ul>\
    </nav>',
    computed: {
        hasPrePage: function hasPrePage() {
            return this.pageIndex === 1 ? "disabled" : "";
        },
        hasNextPage: function hasNextPage() {
            return this.pageIndex === this.totalPage ? "disabled" : "";
        },
        //æ€»é¡µæ•°
        totalPage: function totalPage() {
            //return parseInt((this.totalCount + this.pageSize - 1) / this.pageSize);
            return Math.ceil(this.totalCount / this.pageSize);
        },
        showInputPage: function showInputPage() {
            return this.totalPage > 5;
        },
        //è®¡ç®—æ˜¾ç¤ºå‡ºæ¥çš„æŒ‰é’®é›†åˆ
        navPages: function navPages() {
            var pages = [],
                leftdot = false,
                rightdot = false;

            for (var i = 1; i <= this.totalPage; i++) {
                //é¦–é¡µå’Œæœ€åŽä¸€é¡µå§‹ç»ˆæ˜¾ç¤º
                if (i === 1 || i === this.totalPage && this.totalPage > 1) {
                    pages.push(i);
                    continue;
                } //ç¦»å½“å‰é¡µé¢è¿‘ï¼Œåˆ™æ˜¾ç¤º


                if (Math.abs(this.pageIndex - i) <= this.showNearby) {
                    pages.push(i);
                } else {
                    //ç¦»å¾—è¿œï¼Œåˆ™æ˜¾ç¤ºä¸€æ¬¡ çœç•¥å·
                    if (this.pageIndex > i && !leftdot) {
                        pages.push('...');
                        leftdot = true;
                    }

                    if (this.pageIndex < i && !rightdot) {
                        pages.push('...');
                        rightdot = true;
                    }
                }
            }

            return pages;
        },
        //æ˜¾ç¤ºåˆ†é¡µä¿¡æ¯
        infoContent: function infoContent() {
            var info = this.infoTemplate;
            info = info.replace('{{totalCount}}', this.totalCount).replace('{{pageSize}}', this.pageSize);
            info = info.replace('{{pageIndex}}', this.pageIndex).replace('{{totalPage}}', this.totalPage);
            return info;
        }
    },
    methods: {
        //ç‚¹å‡»æŒ‰é’®æ—¶è§¦å‘
        pageChange: function pageChange(num) {
            this.$emit('page-change', num);
        },
        PrevPageChange: function PrevPageChange() {
            this.$emit('page-change', this.pageIndex - 1);
        },
        NextPageChange: function NextPageChange() {
            this.$emit('page-change', this.pageIndex + 1);
        },
        //æ˜¯å¦å½“å‰é¡µ
        isActive: function isActive(num) {
            return this.pageIndex === num;
        },
        inputPageChange: function inputPageChange() {
            if (/^\d+$/.test(this.inputPageIndex) && this.inputPageIndex > 0 && this.inputPageIndex <= this.totalPage) {
                var index = parseInt(this.inputPageIndex);
                this.$emit('page-change', index);
            }

            this.inputPageIndex = '';
        }
    },
    components: {
        'pager-btn': {
            props: ['num', 'pageIndex'],
            computed: {
                isActive: function isActive() {
                    //è®¡ç®—æ˜¯å¦å½“å‰é¡µæ•°
                    return this.num === this.pageIndex;
                }
            },
            template: "\n  <li :class=\"[{ active: isActive},'page-item']><a class='page-link' href='javascript:void(0)' @click='goto(num,isActive)' :title='num' v-text='num'></a></li> \n            ",
            methods: {
                goto: function goto(num, active) {
                    //å¦‚æžœæ˜¯çœç•¥å·æˆ–è€…å½“å‰é¡µï¼Œåˆ™ä¸åšä»»ä½•ååº”
                    if (active) {
                        return;
                    }

                    this.$emit('page-change', num);
                }
            }
        }
    }
});