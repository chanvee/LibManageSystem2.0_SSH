// Morris.js Charts sample data for SB Admin template

$(function() {
    // Area Chart
	$.getJSON("manage.do?flag=getDayBorrows", function(data){
		 Morris.Line({
		        // ID of the element in which to draw the chart.
		        element: 'day-borrows',
		        // Chart data records -- each entry in this array corresponds to a point on
		        // the chart.
		        data: data,
		        // The name of the data record attribute that contains x-visitss.
		        xkey: 'day',
		        // A list of names of data record attributes that contain y-visitss.
		        ykeys: ['numbers'],
		        // Labels for the ykeys -- will be displayed when you hover over the
		        // chart.
		        labels: ['次数'],
		        // Disables line smoothing
		        smooth: false,
		        resize: true
		    });
	});
	
	$.getJSON("manage.do?flag=getDayBorrowsForUser", function(data){
		 Morris.Line({
		        // ID of the element in which to draw the chart.
		        element: 'user-day-borrows',
		        // Chart data records -- each entry in this array corresponds to a point on
		        // the chart.
		        data: data,
		        // The name of the data record attribute that contains x-visitss.
		        xkey: 'day',
		        // A list of names of data record attributes that contain y-visitss.
		        ykeys: ['numbers'],
		        // Labels for the ykeys -- will be displayed when you hover over the
		        // chart.
		        labels: ['次数'],
		        // Disables line smoothing
		        smooth: false,
		        resize: true
		    });
	});

});
