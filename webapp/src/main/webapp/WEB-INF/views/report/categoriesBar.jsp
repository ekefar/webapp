<%--
  Created by IntelliJ IDEA.
  User: alexander.serebriyan
  Date: 20.12.12
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="container"></div>

<script type="text/javascript">
    $(function () {
        var chart;
        $(document).ready(function () {


            var getTotalForCategory = function (products) {
                var total = 0;
                for (var i = 0; i < products.length; i++) {
                    total += products[i].count;
                }
                return total;
            };

            var getProductsNames = function (products) {
                return products.map(function (product) {
                    return product.product.name
                });
            };

            var getProductsShares = function (products, total) {

                return products.map(function (product) {
                    return parseFloat((product.count / total * 100).toFixed(2));
                });
            };


            var createChart = function (model) {
                console.log("create data", model);
                var colors = Highcharts.getOptions().colors;
                var name = 'Категории товаров';

                // category names
                var categories = [];
                for (var key in model) {
                    if (model.hasOwnProperty(key)) {
                        categories.push(key);
                    }
                }

                console.log(categories);

                // total products count
                var total = 0;
                for (var i = 0; i < categories.length; i++) {
                    var category = categories[i];
                    total += getTotalForCategory(model[category]);
                }

                console.log("total", total);


                var data = [];


                for (var i = 0; i < categories.length; i++) {
                    var category = categories[i];
                    console.log("y", getTotalForCategory(model[category]) / total);
                    console.log("color", colors[i]);
                    console.log("categories", getProductsNames(model[category]));
                    console.log("data", getProductsShares(model[category]));

                    data.push({
                        y: parseFloat((getTotalForCategory(model[category]) / total * 100).toFixed(2)),
                        color: colors[i],
                        drilldown: {
                            name: category,
                            categories: getProductsNames(model[category]),
                            data: getProductsShares(model[category], total)
                        }
                    });
                }


                function setChart(name, categories, data, color) {
                    chart.xAxis[0].setCategories(categories, false);
                    chart.series[0].remove(false);
                    chart.addSeries({
                        name: name,
                        data: data,
                        color: color || 'white'
                    }, false);
                    chart.redraw();
                }

                chart = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container',
                        type: 'column'
                    },
                    title: {
                        text: 'Продажи товаров по категориям'
                    },
                    subtitle: {
                        text: 'Кликните по категории что-бы просмотреть товары. Кликните еще раз для возврата.'
                    },
                    xAxis: {
                        categories: categories
                    },
                    yAxis: {
                        title: {
                            text: 'Процент от продаж'
                        }
                    },
                    plotOptions: {
                        column: {
                            cursor: 'pointer',
                            point: {
                                events: {
                                    click: function () {
                                        var drilldown = this.drilldown;
                                        if (drilldown) { // drill down
                                            setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                                        } else { // restore
                                            setChart(name, categories, data);
                                        }
                                    }
                                }
                            },
                            dataLabels: {
                                enabled: true,
                                color: colors[0],
                                style: {
                                    fontWeight: 'bold'
                                },
                                formatter: function () {
                                    return this.y + '%';
                                }
                            }
                        }
                    },
                    tooltip: {
                        formatter: function () {
                            var point = this.point,
                                    s = this.x + ':<b>' + this.y + '% market share</b><br/>';
                            if (point.drilldown) {
                                s += 'Кликните для просмотра товаров в категории ' + point.category;
                            } else {
                                s += 'Кликните для возврата к категориям';
                            }
                            return s;
                        }
                    },
                    series: [
                        {
                            name: name,
                            data: data,
                            color: 'white'
                        }
                    ],
                    exporting: {
                        enabled: false
                    }
                });
            };

            $.ajax({
                url: "/report/pie/${userId}",
                type: "GET",
                async: false,
                success: function (data) {
                    console.log("success", data);
                    createChart(data);
                }
            });
        });


    });
</script>
</body>
</html>