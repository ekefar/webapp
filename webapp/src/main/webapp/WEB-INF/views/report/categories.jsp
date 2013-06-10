<%--
  Created by IntelliJ IDEA.
  User: alexander.serebriyan
  Date: 20.12.12
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

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


                // Build the data arrays
                var browserData = [];
                var versionsData = [];
                for (var i = 0; i < data.length; i++) {

                    // add browser data
                    browserData.push({
                        name: categories[i],
                        y: data[i].y,
                        color: data[i].color
                    });

                    // add version data
                    for (var j = 0; j < data[i].drilldown.data.length; j++) {
                        var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5;
                        versionsData.push({
                            name: data[i].drilldown.categories[j],
                            y: data[i].drilldown.data[j],
                            color: Highcharts.Color(data[i].color).brighten(brightness).get()
                        });
                    }
                }

                // Create the chart
                chart = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container',
                        type: 'pie'
                    },
                    title: {
                        text: 'Продажи товаров в категориях'
                    },
                    yAxis: {
                        title: {
                            text: 'Процент от общих продаж'
                        }
                    },
                    plotOptions: {
                        pie: {
                            shadow: false
                        }
                    },
                    tooltip: {
                        valueSuffix: '%'
                    },
                    series: [
                        {
                            name: 'Категории',
                            data: browserData,
                            size: '60%',
                            dataLabels: {
                                formatter: function () {
                                    return this.y > 5 ? this.point.name : null;
                                },
                                color: 'white',
                                distance: -30
                            }
                        },
                        {
                            name: 'Товары',
                            data: versionsData,
                            innerSize: '60%',
                            dataLabels: {
                                formatter: function () {
                                    // display only if larger than 1
                                    return this.y > 1 ? '<b>' + this.point.name + ':</b> ' + this.y + '%' : null;
                                }
                            }
                        }
                    ]
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