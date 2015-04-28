'use strict';

describe('Controller: MessagestackCtrl', function () {

  // load the controller's module
  beforeEach(module('webClientApp'));

  var MessagestackCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MessagestackCtrl = $controller('MessagestackCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
