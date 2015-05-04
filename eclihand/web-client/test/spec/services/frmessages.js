'use strict';

describe('Service: frMessages', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var frMessages;
  beforeEach(inject(function (_frMessages_) {
    frMessages = _frMessages_;
  }));

  it('should do something', function () {
    expect(!!frMessages).toBe(true);
  });

});
