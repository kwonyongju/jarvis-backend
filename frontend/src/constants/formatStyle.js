import { css } from "styled-components";

// import { c_error, c_success } from "./colors";

export const f_align_center = css`
  align-items: center;
`;

export const f_align_end = css`
  align-items: flex-end;
`;

export const f_align_top = css`
  align-items: flex-start;
`;

export const f_centered = css`
  align-items: center;
  display: flex;
  justify-content: center;
`;
export const f_column = css`
  display: flex;
  flex-direction: column;
`;

export const f_column_reverse = css`
  flex-direction: column-reverse;
`;

export const f_fit_width = css`
  width: fit-content;
`;

export const f_flex_start = css`
  align-items: flex-start;
  display: flex;
  justify-content: flex-start;
`;

export const f_flex_wrap = css`
  flex-wrap: wrap;
`;

export const f_full_height = css`
  height: 100%;
  max-height: 100%;
`;

export const f_full_width = css`
  max-width: 100%;
  width: 100%;
`;

export const f_half_width = css`
  margin-right: ${(props) => !props.last && "2%"};
  min-width: 0 !important;
  width: 49% !important;
`;

// export const f_highlighted_green = css`
//   box-shadow: 0 0 2.5px ${c_success};
// `;

// export const f_highlighted_red = css`
//   box-shadow: 0 0 6px ${c_error};
// `;

export const f_justify_center = css`
  justify-content: center;
`;

export const f_justify_start = css`
  justify-content: flex-start;
`;

export const f_justify_end = css`
  justify-content: flex-end;
`;

export const f_large_width = css`
  width: 32rem;
`;

export const f_margin_bottom = css`
  margin-bottom: 1rem;
`;

export const f_margin_bottom_double = css`
  margin-bottom: 2rem;
`;

export const f_margin_bottom_half = css`
  margin-bottom: 0.5rem;
`;

export const f_margin_buffer = css`
  margin: 4px;
`;

export const f_margin_offset_left = css`
  margin-left: -4px;
`;

export const f_margin_half_left = css`
  margin-left: 0.5rem;
`;

export const f_margin_half_top = css`
  margin-top: 0.5rem;
`;

export const f_margin_left = css`
  margin-left: 1rem;
`;

export const f_margin_left_half = css`
  margin-left: 0.5rem;
`;

export const f_margin_skinny = css`
  margin-bottom: 0.5rem;
  margin-top: 0.5rem;
`;

export const f_margin_right = css`
  margin-right: 1rem;
`;

export const f_margin_right_double = css`
  margin-right: 2rem;
`;

export const f_margin_right_half = css`
  margin-right: 0.5rem;
`;

export const f_margin_top = css`
  margin-top: 1rem;
`;

export const f_margin_top_double = css`
  margin-top: 2rem;
`;

export const f_margin_top_half = css`
  margin-top: 0.5rem;
`;

export const f_manual_max_width = css`
  max-width: ${(props) => `${props.manualMaxWidth}`};
`;

export const f_last_bottom = css`
  margin-bottom: 0;
`;

export const f_last_right = css`
  margin-right: 0;
`;

export const f_no_wrap = css`
  flex-wrap: nowrap;
`;

export const f_pointer = css`
  cursor: pointer;
`;

export const f_relative = css`
  position: relative;
`;

export const f_row_reverse = css`
  flex-direction: row-reverse;
`;

export const f_skinny_width = css`
  width: 8rem;
`;

export const f_small_text = css`
  input {
    font-size: 0.7rem;
  }
`;

export const f_third_width = css`
  margin-right: 2%;
  width: 32%;
`;

export const f_space_between = css`
  display: flex;
  justify-content: space-between;
`;

export const FormatStyles = css`
  ${(props) => props.alignCenter && f_align_center};
  ${(props) => props.alignEnd && f_align_end};
  ${(props) => (props.alignTop || props.alignStart) && f_align_top};
  ${(props) => props.centered && f_centered};
  ${(props) => props.column && f_column};
  ${(props) => props.columnReverse && f_column_reverse};
  ${(props) => props.fitWidth && f_fit_width};
  ${(props) => props.flexStart && f_flex_start};
  ${(props) => props.flexWrap && f_flex_wrap};
  ${(props) => props.fullHeight && f_full_height};
  ${(props) => props.fullSize && f_full_width};
  ${(props) => props.fullWidth && f_full_width};
  ${(props) => props.half && f_half_width};
  ${(props) => props.justifyStart && f_justify_start};
  ${(props) => props.justifyCenter && f_justify_center};
  ${(props) => props.justifyEnd && f_justify_end};
  ${(props) => props.lastBottom && f_last_bottom};
  ${(props) => props.lastRight && f_last_right};
  ${(props) => props.largeWidth && f_large_width};
  ${(props) => props.manualMaxWidth && f_manual_max_width};
  ${(props) => props.marginBottom && f_margin_bottom};
  ${(props) => props.marginBottomDouble && f_margin_bottom_double};
  ${(props) => props.marginBottomHalf && f_margin_bottom_half};
  ${(props) => props.marginBuffer && f_margin_buffer};
  ${(props) => props.marginHalfLeft && f_margin_half_left};
  ${(props) => props.marginLeft && f_margin_left};
  ${(props) => props.marginLeftHalf && f_margin_left_half};
  ${(props) => props.marginSkinny && f_margin_skinny};
  ${(props) => props.marginRight && f_margin_right};
  ${(props) => props.marginRightDouble && f_margin_right_double};
  ${(props) => props.marginRightHalf && f_margin_right_half};
  ${(props) => props.marginHalfTop && f_margin_half_top};
  ${(props) => props.marginTop && f_margin_top};
  ${(props) => props.marginTopDouble && f_margin_top_double};
  ${(props) => props.marginTopHalf && f_margin_top_half};
  ${(props) => props.noWrap && f_no_wrap};
  ${(props) => props.pointer && f_pointer};
  ${(props) => props.relative && f_relative};
  ${(props) => props.rowReverse && f_row_reverse};
  ${(props) => props.skinny && f_skinny_width};
  ${(props) => props.smallText && f_small_text};
  ${(props) => props.spaceBetween && f_space_between};
`;
// ${(props) => props.highlighted === "green" && f_highlighted_green};
//   ${(props) => props.highlighted === "red" && f_highlighted_red};
