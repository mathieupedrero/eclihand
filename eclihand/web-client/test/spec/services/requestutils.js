'use strict';

describe('Service: RequestUtils', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var RequestUtils;
  beforeEach(inject(function (_RequestUtils_) {
    RequestUtils = _RequestUtils_;
  }));

  it('should do something', function () {
    expect(!!RequestUtils).toBe(true);
  });

});
