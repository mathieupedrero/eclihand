'use strict';

describe('Directive: eclListToolTip', function () {

  // load the directive's module
  beforeEach(module('webClientApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<ecl-list-tool-tip></ecl-list-tool-tip>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the eclListToolTip directive');
  }));
});
