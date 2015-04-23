'use strict';

describe('Service: httpEclihandServerInterceptor', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var httpEclihandServerInterceptor;
  beforeEach(inject(function (_httpEclihandServerInterceptor_) {
    httpEclihandServerInterceptor = _httpEclihandServerInterceptor_;
  }));

  it('should do something', function () {
    expect(!!httpEclihandServerInterceptor).toBe(true);
  });

});
