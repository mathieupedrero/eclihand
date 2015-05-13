'use strict';

describe('Service: surfaceConstraintChecks', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var surfaceConstraintChecks;
  beforeEach(inject(function (_surfaceConstraintChecks_) {
    surfaceConstraintChecks = _surfaceConstraintChecks_;
  }));

  it('should do something', function () {
    expect(!!surfaceConstraintChecks).toBe(true);
  });

});
