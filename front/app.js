angular.module("strong-pass", []).controller("spController", [
    "$http", "$scope",
    function spController($http, $scope) {
        this.length = 8;
        this.numbers = false;
        this.symbols = false;

        this.alarm = function () {
            window.alert("hi");
        };
        this.showStates = function (length, c1, c2) {
            var request = {
                length: length,
                numbers: c1,
                symbols: c2
            }
            $http({
                method: "POST",
                url: "http://localhost:8080/generate",
                data: request,
            }).then(function successCallback(response) {
                $scope.result = response.data.result
            }, function errorCallback(response) {
                console.log(response)
            })
        }
        this.copy = function () {
            var text = document.getElementById('result');
            if (text.value != "Copied!") {
                text.select();
                document.execCommand("copy");
                $scope.result = "Copied!";
            }
        }
    }
]);