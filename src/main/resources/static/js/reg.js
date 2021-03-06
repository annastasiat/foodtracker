angular.module("registration_form",[])
    .controller("RegistrationCtrl", function ($scope, $http) {
        console.log("regCtrl");
        $scope.user = {};
        $scope.showSuccess = false;
        $scope.showError = false;

        $scope.addUser = function(user){
            console.log("adding");
            $http({
                method: "POST",
                url: "/api/registration/register",
                data: $.param(user),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                    console.log("succes");
                    $scope.showSuccess = true;
                    $scope.showError = false;
                },
                (error) => {
                    console.log("error");
                    $scope.showSuccess = false;
                    $scope.showError = true;
                }
            );
        }
    });