package com.pedrero.eclihand.controller.security;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.servlet.SpringVaadinServlet;

public abstract class SecurityRuleUtils {

	private static final SecurityController SECURITY_CONTROLLER = SpringVaadinServlet.WEB_APPLICATION_CONTEXT
			.getBean(SecurityController.class);

	public static ISecurityRule and(final ISecurityRule firstRule, final ISecurityRule secondRule,
			final ISecurityRule... otherRules) {
		return new ISecurityRule() {
			@Override
			public Boolean isAccessAllowed() {
				if (!firstRule.isAccessAllowed()) {
					return false;
				}
				if (!secondRule.isAccessAllowed()) {
					return false;
				}
				if (otherRules.length != 0) {
					for (ISecurityRule rule : otherRules) {
						if (!rule.isAccessAllowed()) {
							return false;
						}
					}
				}
				return true;
			}
		};
	}

	public static ISecurityRule or(final ISecurityRule firstRule, final ISecurityRule secondRule,
			final ISecurityRule... otherRules) {
		return new ISecurityRule() {
			@Override
			public Boolean isAccessAllowed() {
				if (firstRule.isAccessAllowed()) {
					return true;
				}
				if (secondRule.isAccessAllowed()) {
					return true;
				}
				if (otherRules.length != 0) {
					for (ISecurityRule rule : otherRules) {
						if (!rule.isAccessAllowed()) {
							return true;
						}
					}
				}
				return false;
			}
		};
	}

	public static ISecurityRule not(final ISecurityRule rule) {
		return new ISecurityRule() {
			@Override
			public Boolean isAccessAllowed() {
				return !rule.isAccessAllowed();
			}
		};
	}

	public static ISecurityRule userHasOneOf(final Credential... otherCredentials) {
		return new ISecurityRule() {
			@Override
			public Boolean isAccessAllowed() {
				return SECURITY_CONTROLLER.userHasCredentialIn(otherCredentials);
			}
		};
	}

	public static ISecurityRule userHas(final Credential credential) {
		return new ISecurityRule() {
			@Override
			public Boolean isAccessAllowed() {
				return SECURITY_CONTROLLER.userHasCredential(credential);
			}
		};
	}
}
