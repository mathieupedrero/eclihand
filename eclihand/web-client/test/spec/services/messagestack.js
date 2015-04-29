'use strict';

describe('Service: messageStack', function () {

  // load the service's module
  beforeEach(module('webClientApp'));

  // instantiate service
  var messageStack;
  beforeEach(inject(function (_messageStack_) {
    messageStack = _messageStack_;
  }));

  it('should do something', function () {
    expect(!!messageStack).toBe(true);
  });

});
