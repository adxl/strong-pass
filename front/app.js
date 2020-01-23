angular
  .module("strong-pass", [])
  .controller("spController", ["$http", function spController($http) {
    this.numbers = false;
    this.symbols = false;

    this.alarm = function() {
      window.alert("hi");
    };

    this.showStates = function(a, c1, c2) {
      console.log(c1);
      console.log(c2);
    };
  }]);
