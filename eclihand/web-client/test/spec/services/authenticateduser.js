'use strict';

describe('Service: authenticatedUser', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var authenticatedUser;
  beforeEach(inject(function (_authenticatedUser_) {
    authenticatedUser = _authenticatedUser_;
  }));

  it('should do something', function () {
    expect(!!authenticatedUser).toBe(true);
  });

});
