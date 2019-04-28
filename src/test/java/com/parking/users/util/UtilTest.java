package com.parking.users.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.parking.users.jsonapi.ErrorDetail;
import com.parking.users.jsonapi.JsonApiError;

import static com.parking.users.builders.ErrorDetailBuilder.anErrorDetail;
import static com.parking.users.builders.JsonApiErrorBuilder.aJsonApiError;

@RunWith(MockitoJUnitRunner.class)
public class UtilTest {

	@InjectMocks
	private Util utilMock;

	@Test
	public void buildError() {
		// Arrange
		ErrorDetail errorDetail = anErrorDetail().build();
		JsonApiError expected = aJsonApiError().with(anErrorDetail()).build();

		// Act
		JsonApiError actual = this.utilMock.buildError(errorDetail.getId(), errorDetail.getCode(),
				errorDetail.getTitle(), errorDetail.getStatus(), errorDetail.getSource(), errorDetail.getDetail());
		// Assert
		assertEquals(expected.getErrors().get(0).getCode(), actual.getErrors().get(0).getCode());
	}
}
