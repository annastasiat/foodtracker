angular.module("statistics", [])
    .controller("StatisticsCtrl", ["$scope", "$http", function ($scope, $http) {
        console.log("statisticsCtrl")

        $scope.todaysFood = [];
        $scope.allFood = [];
        $scope.userStat = {};
        $scope.getTodaysFood = function () {
            $http({
                method: "GET",
                url: "/api/user/todays_food",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    $scope.todaysFood = data.data.usersFood;
                },
                function (error) {
                    console.log("users error")
                }
            );
        }
        $scope.getAllFood = function () {
            $http({
                method: "GET",
                url: "/api/user/all_food",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    $scope.allFood = data.data.usersFood;
                },
                function (error) {
                    console.log("users error")
                }
            );
        }
        $scope.getuserStat = function () {
            $http({
                method: "GET",
                url: "/api/user/user_statistics",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    $scope.userStat = data.data;
                },
                function (error) {
                    console.log("users error")
                }
            );
        }


    }]);