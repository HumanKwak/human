<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.min.js" integrity="sha512-GMGzUEevhWh8Tc/njS0bDpwgxdCJLQBWG3Z2Ct+JGOpVnEmjvNx6ts4v6A2XJf1HOrtOsfhv3hBKpK9kE5z8AQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>2021. 11. 2.오전 11:06:34</title>
</head>
<body>
	<div id="chart">
		<canvas id="ctx"></canvas>
	</div>
</body>
<script>
var year='${year}';
	$(function() {
		$.getJSON("/sales/" +year)
		.done(function(data) {
			console.log(data);
			var ctx = $("#ctx")[0].getContext('2d');
			var labels = data.map(obj=>obj.MONTH);
			var datas = data.map(obj=>obj.SALES);
			console.log(labels);
			console.log(datas);
			var bgColer = [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ];
			var borderColer = [
				'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
			];
			
			var myChart = new Chart(ctx, {
			    type: 'line',
			    data: {
			        labels: ['월별 매출 현황'],
			        datasets: [{
			            label: labels,
			            data: datas,
			            backgroundColors:bgColer,
			            borderWidth: 1
			        }]
			    }
			    
			});
		})
	});
	
</script>
</html>