'use strict';

describe('Directive: eclTable', function () {

  // load the directive's module
  beforeEach(module('webClientApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<ecl-table></ecl-table>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the eclTable directive');
  }));
});
