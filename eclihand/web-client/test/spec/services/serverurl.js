'use strict';

describe('Service: serverUrl', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var serverUrl;
  beforeEach(inject(function (_serverUrl_) {
    serverUrl = _serverUrl_;
  }));

  it('should do something', function () {
    expect(!!serverUrl).toBe(true);
  });

});
