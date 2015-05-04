'use strict';

describe('Service: enMessages', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var enMessages;
  beforeEach(inject(function (_enMessages_) {
    enMessages = _enMessages_;
  }));

  it('should do something', function () {
    expect(!!enMessages).toBe(true);
  });

});
