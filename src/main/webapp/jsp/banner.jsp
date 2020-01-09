<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function () {
        $("#bannerTable").jqGrid(
            {
                url : "${pageContext.request.contextPath}/banner/queryByPage",
                datatype: "json",
                colNames: ['ID', 'Title', 'Url','href','发布时间','描述','状态'],
                colModel: [
                    {name: 'id', index: 'id',align:"center,hidden:true"},
                    {name: 'title', index: 'invdate', width: 90,editable: true},
                    {
                        name: 'url', formatter: function (data) {
                            return "<img style='width: 180px;height: 80px' src='"+data+"'>"
                        }
                        ,editable: true ,edittype:"file",editoptions: {enctype:"multipart/form-data"}
                    },
                    {name: 'href', index: 'invdate', width: 90,editable: true},
                    {name: 'createDate', index: 'invdate', width: 90,editable: true},
                    {name: 'desction', index: 'invdate', width: 90,editable: true},
                    {name : 'status',align:"center",formatter:function (data) {
                            if (data=="1"){
                                return "展示";
                            } else return "冻结";
                        },editable:true,editrules:{required:true},edittype:"select",editoptions: {value:"1:展示;2:冻结"}}

                ],
                rowNum: 3,
                rowList: [10, 20, 30],
                pager: '#bannerPage',
                sortname: 'id',
                // 限定请求方式
                mtype: "get",
                viewrecords: true,
                sortorder: "desc",
                caption: "轮播图信息",
                autowidth: true,
                multiselect:true,
                styleUI: "Bootstrap",
                height: 400,
                autowidth: true,
                editurl: "${pageContext.request.contextPath}/banner/save"
            });

        $("#bannerTable").jqGrid('navGrid', '#bannerPage', {edit: true, add: true, del: true},
            {
                closeAfterEdit: true,
                afterSubmit:function (response,postData) {
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/loadBanner",
                        type:"post",
                        datatype: "json",
                        data:{bannerId:bannerId},
                        fileElementId:"url",
                        success:function (data) {
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    })
                    return postData;
                }
            },{
                closeAfterAdd:true,
                afterSubmit:function (response,postData) {
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/loadBanner",
                        type:"post",
                        datatype: "json",
                        data:{bannerId:bannerId},
                        fileElementId:"url",
                        success:function (data) {
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    })
                    return postData;
                }
            },{
                closeAfterDel:true
            });
        //点击下载触发事件
        $("#dc").click(function(){
            $.ajax({
                url: "${pageContext.request.contextPath}/banner/dc",
                datatype: "json",
                type: "post",
                success: function (data) {
                     alert("导出成功");
                },
            })
        })

    })
    function importBanner(){
        $.ajaxFileUpload({
                url: "${pageContext.request.contextPath}/banner/importBanner",
                datatype:"json",
                type: "post",
                fileElementId:"inputBanner",
                success :function (data) {
                    if(data.status=="200")
                        alert("导入成功")
                    $("#myModal2").modal("hide");
                    $("#bannerTable").trigger("reloadGrid");
                }
            }
        )
    }
    function downloadBanner(){
        $.ajax({
            url: "${pageContext.request.contextPath}/banner/downloadBanner",
            datatype:"json",
            type: "post",
            success :function (data) {
                alert("下载成功")
            }
        })
    }
</script>
<div>
    <h4>轮播图管理</h4>
</div>
<ul class="nav nav-tabs">
    <li><a>轮播图信息</a></li>
    <li id="dc" ><a href="javascript:void(0)">导出轮播图信息</a></li>
    <li><a data-toggle="modal" data-target="#myModal2">轮播图导入</a></li>
<%--
--%>
</ul>
<table id="bannerTable"></table>
<div id="bannerPage" style="height: 50px"></div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel2">提交banner</h4>
            </div>
            <div class="modal-body"><input id= "inputBanner"  name = "inputBanner" type="file"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="importBanner()">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>