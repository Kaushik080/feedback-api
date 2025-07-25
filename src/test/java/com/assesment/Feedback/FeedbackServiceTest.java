package com.assesment.Feedback;

import com.assesment.Feedback.model.Feedback;
import com.assesment.Feedback.repo.FeedbackRepo;
import com.assesment.Feedback.service.FeedbackService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    private FeedbackRepo feedbackRepo;

    @InjectMocks
    private FeedbackService feedbackService;

    private Feedback validFeedback;

    @BeforeEach
    void setUp() {
        validFeedback = new Feedback();
        validFeedback.setId(1L);
        validFeedback.setName("John Doe");
        validFeedback.setEmail("john.doe@example.com");
        validFeedback.setMessage("This is a great service!");
        validFeedback.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void saveFeedback_SavesSuccessfully() {
        when(feedbackRepo.save(validFeedback)).thenReturn(validFeedback);

        Feedback result = feedbackService.saveFeedback(validFeedback);

        assertNotNull(result, "Saved feedback should not be null");
        assertEquals(validFeedback, result, "Saved feedback should match input");
        verify(feedbackRepo, times(1)).save(validFeedback);
        verifyNoMoreInteractions(feedbackRepo);
    }

    @Test
    void NullFeedback_ThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> feedbackService.saveFeedback(null),
                "Expected IllegalArgumentException for null feedback"
        );
        verifyNoInteractions(feedbackRepo);
    }


    static Stream<Arguments> invalidFeedbackTestCases() {

        Feedback invalidEmailFeedback = new Feedback();
        invalidEmailFeedback.setId(2L);
        invalidEmailFeedback.setName("Jane Doe");
        invalidEmailFeedback.setEmail("invalid-email");
        invalidEmailFeedback.setMessage("This is a valid message.");
        invalidEmailFeedback.setCreatedAt(LocalDateTime.now());

        Feedback blankNameFeedback = new Feedback();
        blankNameFeedback.setId(3L);
        blankNameFeedback.setName("");
        blankNameFeedback.setEmail("jane.doe@example.com");
        blankNameFeedback.setMessage("This is a valid message.");
        blankNameFeedback.setCreatedAt(LocalDateTime.now());

        Feedback shortMessageFeedback = new Feedback();
        shortMessageFeedback.setId(4L);
        shortMessageFeedback.setName("Jane Doe");
        shortMessageFeedback.setEmail("jane.doe@example.com");
        shortMessageFeedback.setMessage("Short");
        shortMessageFeedback.setCreatedAt(LocalDateTime.now());

        Feedback shortNameFeedback = new Feedback();
        shortNameFeedback.setId(5L);
        shortNameFeedback.setName("Jo");
        shortNameFeedback.setEmail("john.doe@example.com");
        shortNameFeedback.setMessage("This is a very long and valid message for the feedback.");
        shortNameFeedback.setCreatedAt(LocalDateTime.now());

        return Stream.of(
                Arguments.of(invalidEmailFeedback, "Invalid email format"),
                Arguments.of(blankNameFeedback, "Name cannot be blank"),
                Arguments.of(shortMessageFeedback, "Message must be at least 10 characters"),
                Arguments.of(shortNameFeedback, "Name must be at least 3 characters")
        );
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidFeedbackTestCases")
    void InvalidInput_ThrowsConstraintViolationException(
            Feedback invalidFeedback, String expectedErrorMessagePart) {
        when(feedbackRepo.save(invalidFeedback))
                .thenThrow(new ConstraintViolationException(expectedErrorMessagePart, null));

        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> feedbackService.saveFeedback(invalidFeedback),
                "Expected ConstraintViolationException for invalid input"
        );

        assertTrue(exception.getMessage().contains(expectedErrorMessagePart),
                "Exception message should contain: " + expectedErrorMessagePart);
        verify(feedbackRepo, times(1)).save(invalidFeedback);
        verifyNoMoreInteractions(feedbackRepo);
    }

}