'use strict';

describe('Service: eclUtils', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var eclUtils;
  beforeEach(inject(function (_eclUtils_) {
    eclUtils = _eclUtils_;
  }));

  it('should do something', function () {
    expect(!!eclUtils).toBe(true);
  });

});
